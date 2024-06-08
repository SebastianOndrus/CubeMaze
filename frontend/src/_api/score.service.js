//get api/comment/cubeMaze
//post api/comment - comment

import gsAxios from "./index";
import {formatDate} from "./utils";

// const fetchComments = function (game) {
//     return gsAxios.get('/comment' + game);
// }

export const fetchScore =  game => gsAxios.get('/score/' + game);
export const addScore = (game,player,level,points) => gsAxios.post('/score', {
    player: player,
    game: game,
    level: level,
    points: points,
    playedAt: formatDate(new Date()),
});