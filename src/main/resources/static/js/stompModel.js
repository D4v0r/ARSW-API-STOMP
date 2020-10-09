let app2 = (() => {

    let seats = [
        [true, true, true, true, true, true, true, true, true, true, true, true],
        [true, true, true, true, true, true, true, true, true, true, true, true],
        [true, true, true, true, true, true, true, true, true, true, true, true],
        [true, true, true, true, true, true, true, true, true, true, true, true],
        [true, true, true, true, true, true, true, true, true, true, true, true],
        [true, true, true, true, true, true, true, true, true, true, true, true],
        [true, true, true, true, true, true, true, true, true, true, true, true]];

    let positions = [];
    class rectPosition{
        constructor(x, y) {
            this.x = x;
            this.y = y;
        }

    }

    let seatsPositions = undefined;

    class Seat {
        constructor(row, col) {
            this.row = row;
            this.col = col;
        }
    }
    

    let stompClient = null;


    let getMousePosition = (evt) => {
        canvas = document.getElementById("myCanvas");
        let rect = canvas.getBoundingClientRect();
        return {
            x: evt.clientX - rect.left,
            y: evt.clientY - rect.top
        };
    };
    
    let drawSeats = (cinemaFunction) => {
        let c = document.getElementById("myCanvas");
        let ctx = c.getContext("2d");
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
            let positionsRow =[];
            for (let j = 0; j < seats[i].length; j++) {
                if (seats[i][j]) {
                    ctx.fillStyle = "#009900";
                } else {
                    ctx.fillStyle = "#FF0000";
                }
                col++;
                ctx.fillRect(20 * col, 20 * row, 20, 20);
                positionsRow.push(new rectPosition(20 * col, 20* row));
                col++;
            }
            positions.push(positionsRow);
            row++;
        }
    };

    let connectAndSubscribe = () => {
        console.info('Connecting to WS...');
        let socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);

        //subscribe to /topic/TOPICXX when connections succeed
        stompClient.connect({},  (frame) => {
            console.log('Connected: ' + frame);
            stompClient.subscribe("/topic/buyticket", message => {
                changeSeatColor(message);
            });
        });
    };

    const changeSeatColor = (message) => {
        const {row, col} = JSON.parse(message.body);
        seats[row][col] =  false;
        drawSeats();
    };
    const verifyAvailability =  (row,col) => {
        if(row === -1){
            console.info("This is not a seat");
            return;
        }
        if (seats[row][col]){
            seats[row][col] = false;
            console.info("purchased ticket");
            stompClient.send("/topic/buyticket", {}, JSON.stringify(new Seat(row, col)));
        } else{
            console.info("Ticket not available");
        }  

    };

    const calculateSeatPosition = ({x, y}) =>{
        for(let i = 0; i < positions.length; i ++){
            for(let j = 0; j < positions[i].length; j++){
                const rect = positions[i][j];
                if(rect.x <= x  && x <= rect.x + 20 &&  rect.y  <= y && y <= rect.y + 20){
                    return {
                        row: i,
                        col: j,
                    }
                }
            }
        }
        return {row: -1, col: -1}
    };

    const buyTicketAction = (event) => {
        const {row, col} = calculateSeatPosition(getMousePosition(event));
        verifyAvailability(row, col);
    };

    return {

        init: () => {
            drawSeats();
            //websocket connection
            connectAndSubscribe();
        },

        buyTicket: () => {
            const canvas = document.getElementById("myCanvas");
            canvas.addEventListener("click", event => buyTicketAction(event));
        },

        disconnect: () => {
            if (stompClient !== null) {
                stompClient.disconnect();
            }
            setConnected(false);
            console.log("Disconnected");
        }
    };
})();