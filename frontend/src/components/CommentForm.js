import {Button, Form, InputGroup} from "react-bootstrap";
import { useForm } from "react-hook-form";
import React from "react";


function CommentForm({onSendComment, player}) {
    const { register, handleSubmit, formState: { errors, isValid } } = useForm();
    const onSubmit = data => {
        onSendComment(data.comment);
    };

    if (!player) {
        return <p className="no_logged_user">Log in to add comment</p>;
    }

    return (
        <Form onSubmit={handleSubmit(onSubmit)}>
            <h4 className={'add-comment'}>Add comment:</h4>
            <InputGroup className="mb-3">
                <input className="form-control"
                       type="text"
                       autoComplete={"off"}
                       {...register("comment", {
                           minLength: { value: 3, message: "Comment must be at least 3 characters long" },
                           maxLength: { value: 150, message: "Comment must be at most 150 characters long" },
                           required: { value: true, message: "Comment message is required" }
                       })}
                       placeholder="Enter your comment" />

                <Button
                    variant="outline-primary"
                    id="button-addon1"
                    type="submit"
                    disabled={!isValid}
                    style={{color: 'black', backgroundColor: 'lightskyblue'}}>
                    <h4 className="add-comment" style={{alignContent: "center", fontWeight: "bold"}}>Send</h4>
                </Button>
            </InputGroup>
            <Form.Text style={{color: 'red', float: 'right'}}>
                {errors.comment?.message}
            </Form.Text>

        </Form>
    );
}

export default CommentForm;