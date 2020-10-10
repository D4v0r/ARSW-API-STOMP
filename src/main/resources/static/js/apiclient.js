let apiclient = (()=>{
    const URL = "http://localhost:8080/cinemas";
    return{
        getFunctionsByCinema(cinemaName, callback){
            $.get(`${URL}/${cinemaName}`, callback);
        },
        getFunctionsByCinemaAndDate(cinemaName, date, callback){
            $.get(`${URL}/${cinemaName}/${date}`, callback);
        },

        putFunction(cinemaName, cinemaFunction, callback){
            let  putPromise = $.ajax({
                url: `${URL}/${cinemaName}`,
                type: 'PUT',
                data: JSON.stringify(cinemaFunction),
                contentType: "application/json",
            });
            putPromise.then(res => callback(res));
        },

        postFunction(cinemaName, cinemaFunction, callback){
            let  putPromise = $.ajax({
                url: `${URL}/${cinemaName}`,
                type: 'POST',
                data: JSON.stringify(cinemaFunction),
                contentType: "application/json",
            });
            putPromise.then(res => callback(res));
        },

        deleteFunction(cinemaName, date, movie, callback){
            let  putPromise = $.ajax({
                url: `${URL}/${cinemaName}/${date}/${movie}`,
                type: 'DELETE',
            });
            putPromise.then(res => callback(res));
        },
    }
})();