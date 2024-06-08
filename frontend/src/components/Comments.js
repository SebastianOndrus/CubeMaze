
import React from 'react';
import { MDBTable, MDBTableHead, MDBTableBody } from 'mdb-react-ui-kit';

export default function Comments({comments}) {
    return (
        <div className="centered container-fluid">
        <MDBTable >
            <MDBTableHead className='table-info'>
                <tr>
                    <th scope='col'>Player</th>
                    <th scope='col'>Comment</th>
                    <th scope='col'>Date</th>
                </tr>
            </MDBTableHead>
            <MDBTableBody >
                {comments.map(comment => (
                    <tr tr className='table-light' key={`comment-${comment.ident}`}>
                        <td>{comment.player}</td>
                        <td>{comment.comment}</td>
                        <td>{new Date(comment.commentedAt).toLocaleString()}</td>
                    </tr>
                ))}
            </MDBTableBody>
        </MDBTable>
        </div>
    );
}