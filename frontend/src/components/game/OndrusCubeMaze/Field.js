import Tile from "./Tile";

function Field({ tiles, onMoveCube, cube }) {
    return (
        <table className="minefield">
            <tbody>
            {tiles?.map((row, rowIndex) => (
                <tr key={`row-${rowIndex}`}>
                    {row.map((tile, colIndex) => (
                        (rowIndex === cube.cubeY && colIndex === cube.cubeX) ? (
                            <div key={`tile-${rowIndex}-${colIndex}`}
                                className="square"
                                style={{
                                    borderTopColor: cube.cubeBack.toLowerCase(),
                                    borderRightColor: cube.cubeRight.toLowerCase(),
                                    borderBottomColor: cube.cubeFront.toLowerCase(),
                                    borderLeftColor: cube.cubeLeft.toLowerCase(),
                                    backgroundColor: cube.cubeTop.toLowerCase(),
                                }}
                            />
                        ) : (
                            <Tile
                                key={`tile-${rowIndex}-${colIndex}`}
                                tile={tile}
                                onMoveCube={() => onMoveCube(rowIndex, colIndex)}
                            />
                        )
                    ))}
                </tr>
            ))}
            </tbody>
        </table>
    )
}

export default Field;
