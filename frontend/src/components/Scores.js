
import React from 'react';
import { MDBTable, MDBTableHead, MDBTableBody } from 'mdb-react-ui-kit';

export default function Comments({scores}) {

    return (
        <div className="centered container-fluid">
        <MDBTable className='rounded-5'>
            <MDBTableHead className='table-info'>
                <tr>
                    <th scope='col'>Player</th>
                    <th scope='col'>Points</th>
                    <th scope='col'>Level</th>
                    <th scope='col'>Date</th>
                </tr>
            </MDBTableHead>
            <MDBTableBody >
                {scores.map(score => (
                    <tr tr className='table-light' key={`score-${score.ident}`}>
                        <td>{score.player}</td>
                        <td>{score.points}</td>
                        <td>{score.level}</td>
                        <td>{new Date(score.playedAt).toLocaleString()}</td>
                    </tr>
                ))}
            </MDBTableBody>
        </MDBTable>
        </div>
    );
}