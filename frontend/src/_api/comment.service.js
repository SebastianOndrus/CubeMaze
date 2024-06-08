//get api/comment/cubeMaze
//post api/comment - comment

import gsAxios from "./index";
import {formatDate} from "./utils";

// const fetchComments = function (game) {
//     return gsAxios.get('/comment' + game);
// }

export const fetchComments =  game => gsAxios.get('/comment/' + game);
export const addComment = (game,player,comment) => gsAxios.post('/comment', {
    player: player,
    game: game,
    comment: comment,
    commentedAt: formatDate(new Date()),
});