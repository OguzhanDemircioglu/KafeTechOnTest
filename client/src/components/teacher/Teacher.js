import React, {useEffect, useState} from 'react';
import "./Teacher.css";
import {BASE_URL} from "../../common/constant";
import {Card, ListGroup} from "react-bootstrap";

const Teacher = () => {

    const [item, setItem] = useState([]);

    const findAll = () => {
        fetch(BASE_URL + 'teacher/findAll')
            .then(response => {
                if (!response.ok) {
                    throw new Error('İşlem şuan gerçekleştirilemiyor');
                }
                return response.json();
            })
            .then(data => {
                setItem(data);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    };

    useEffect(() => {

        findAll();

    }, []);


    return (
        <div className="sparePartList">
            {
                item?.map(item => {
                    return (
                        <Card key={item.id}
                              style={{width: "18rem", marginBottom: "10px"}}
                              className="SparePartListCard"
                        >
                            <Card.Body>
                                <Card.Title>{item.name} {item.surname}</Card.Title>
                                <Card.Text>
                                    {item.tckn}
                                </Card.Text>
                            </Card.Body>
                        </Card>
                    )
                })
            }
        </div>
    );
};

export default Teacher;