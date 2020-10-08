const app = (function () {

    let seats = [[true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true]];
    let c,ctx;
    
    class Seat {
        constructor(row, col) {
            this.row = row;
            this.col = col;
        }
    }
    

    let stompClient = null;

    //get the x, y positions of the mouse click relative to the canvas
    const getMousePosition = evt =>  {
        $('#myCanvas').click(e => {
            const rect = canvas.getBoundingClientRect();
            const x = e.clientX - rect.left;
            const y = e.clientY - rect.top;
            console.info(x);
            console.info(y);
        });
  
    };
    
    const drawSeats = (cinemaFunction) => {
        c = document.getElementById("myCanvas");
        ctx = c.getContext("2d");
        ctx.fillStyle = "#001933";
        ctx.fillRect(100, 20, 300, 80);
        ctx.fillStyle = "#FFFFFF";
        ctx.font = "40px Arial";
        ctx.fillText("Screen", 180, 70);
        let row = 5;
        let col = 0;
        for (let i = 0; i < seats.length; i++) {
            row++;
            col = 0;
            for (let j = 0; j < seats[i].length; j++) {
                if (seats[i][j]) {
                    ctx.fillStyle = "#009900";
                } else {
                    ctx.fillStyle = "#FF0000";
                }
                col++;
                ctx.fillRect(20 * col, 20 * row, 20, 20);
                col++;
            }
            row++;
        }
    };

    const connectAndSubscribe = () =>  {
        console.info('Connecting to WS...');
        const socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);

        //subscribe to /topic/TOPICXX when connections succeed
        stompClient.connect({}, frame => {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/buyticket', eventbody => {
                console.alert("evento recibido");
                const theObject = JSON.parse(eventbody.body);

            });
        });

    };

    const verifyAvailability = (row, col) => {
        const st = new Seat(row, col);
        if (seats[row][col] === true) {
            seats[row][col] = false;
            console.info("purchased ticket");
            stompClient.send("/topic/buyticket", {}, JSON.stringify(st));

        } else {
            console.info("Ticket not available");
        }

    };


    return {

        init: function () {
            const can = document.getElementById("canvas");
            drawSeats();
            //websocket connection
            connectAndSubscribe();
        },

        buyTicket: function (row, col) {
            console.info("buying ticket at row: " + row + "col: " + col);
            verifyAvailability(row,col);

            //buy ticket
        },

        disconnect: function () {
            if (stompClient !== null) {
                stompClient.disconnect();
            }
            setConnected(false);
            console.log("Disconnected");
        }
    };

})();