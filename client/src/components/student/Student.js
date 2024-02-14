import React, {useEffect, useState} from 'react';
import "../../App.css";
import {BASE_URL} from "../../common/constant";
import {Button} from "@mui/material";

const Student = message => {

    const [item, setItem] = useState([]);
    const [indexCount, setIndexCount] = useState([]);
    const [itemCount, setItemCount] = useState([]);
    const [name, setName] = useState(null);
    const [surname, setSurname] = useState(null);
    const [tckn, setTckn] = useState(null);
    const [itemId, setItemId] = useState(-1);

    const findAll = () => {
        fetch(BASE_URL + 'student/findAll')
            .then(response => {
                if (!response.ok) {
                    throw new Error('İşlem şuan gerçekleştirilemiyor');
                }
                return response.json();
            })
            .then(data => {
                setItem(data);
                setIndexCount(data.length+1);
                setItemCount(data[data.length-1].id+1);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    };

    const insertStudent = () =>{
        fetch(BASE_URL + 'student/save', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ name, surname, tckn })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('İşlem şuan gerçekleştirilemiyor');
                }
                return response.json();
            })
            .then(data => {})
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }

    function deleteStudent () {
        fetch(BASE_URL + `student/deleteStudentById/${itemId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('İşlem şuan gerçekleştirilemiyor');
                }
                return response.json();
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }

    useEffect(() => {
        findAll();
    }, []);

    return (
        <>
            <table>
                <thead>
                <tr>
                    <th></th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>TCKN</th>
                    <th>About</th>
                    <th>Crud</th>
                </tr>
                </thead>
                <tbody>
                {
                    item?.map((item, index) => {
                        return (
                            <tr key={index}>
                                <td>{index + 1}</td>
                                <td>{item.id}</td>
                                <td>{item.name}</td>
                                <td>{item.surname}</td>
                                <td>{item.tckn}</td>
                                <td>
                                    <Button type="button">Detail</Button>
                                </td>
                                <td>
                                    <Button onMouseUp={()=>{setItemId(item.id)}}
                                            onClick={deleteStudent}
                                            >Delete</Button>
                                </td>
                            </tr>
                        )
                    })
                }
                <tr>
                    <td>{indexCount}</td>
                    <td>{itemCount}</td>
                    <td>
                        <input type="text" placeholder="Enter Name"
                               onChange={e => setName(e.target.value)}/>
                    </td>
                    <td>
                        <input type="text" placeholder="Enter Surname"
                               onChange={e => setSurname(e.target.value)}/>
                    </td>
                    <td>
                        <input type="number" placeholder="Enter TCKN"
                               onChange={e => setTckn(e.target.value)}/>
                    </td>
                    <td/>
                    <td>
                        <Button type={"submit"} onClick={insertStudent}>Add</Button>
                    </td>
                </tr>

                </tbody>
            </table>
        </>
    );
};

export default Student;