
import gsAxios from "./index";
import {formatDate} from "./utils";

export const fetchAverageRating =  game => gsAxios.get('/rating/' + game);
export const fetchRating =  (game,player) => gsAxios.get('/rating/' + game + '/' + player);
export const addRating = (game,player,rating) => gsAxios.post('/rating', {
    player: player,
    game: game,
    rating: rating,
    ratedOn: formatDate(new Date()),
});


