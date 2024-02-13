import React, {useEffect, useState} from 'react';
import "./Lesson.css";
import {BASE_URL} from "../../common/constant";
import {Card} from "react-bootstrap";

const Lesson = () => {

        const [item, setItem] = useState([]);

        const findAll = () => {
                fetch(BASE_URL + 'lesson/findAll')
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
                                                        <Card.Title>
                                                                Subject -> {item.name}
                                                        </Card.Title>
                                                        <Card.Text>
                                                                Number of Lessons Per Week -> {item.countPerWeek}
                                                        </Card.Text>
                                                </Card.Body>
                                        </Card>
                                    )
                            })
                    }
            </div>
        );
};

export default Lesson;