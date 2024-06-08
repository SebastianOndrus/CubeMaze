import gsAxios from "../../../../_api";

const FIELD_URL = '/cubeMaze/field';
const NEW_GAME_URL =  FIELD_URL +"/newGame";
const MOVE_URL =  FIELD_URL +"/move";


const fetchField = () => gsAxios.get(FIELD_URL);
const newGame = (level) => gsAxios.get(`${NEW_GAME_URL}?level=${level}`);
const moveCube = (row,column) => gsAxios.get(`${MOVE_URL}?row=${row}&column=${column}`);

const fieldService = {fetchField, newGame, moveCube};
export default fieldService;