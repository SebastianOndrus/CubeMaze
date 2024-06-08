
function Tile({tile, onMoveCube }) {
    let tileClass;
    if (tile === 1) {
        tileClass = 'road';
    } else if (tile === 2) {
        tileClass = 'finish';
    } else if (tile === 23) {
        tileClass = 'marked_red';
    } else if (tile === 22) {
        tileClass = 'marked_blue';
    } else if (tile === 21) {
        tileClass = 'marked_none';
    } else if (tile === 13) {
        tileClass = 'marker_red';
    } else if (tile === 12) {
        tileClass = 'marker_blue';
    } else if (tile === 11) {
        tileClass = 'marker_none';
    }
    else {
        tileClass = 'empty';
    }

    const handleClick = () => {
        onMoveCube();
    }
    return (
        <td className={tileClass} onClick={handleClick}>
            <span></span>
        </td>

    )

}
export default Tile;