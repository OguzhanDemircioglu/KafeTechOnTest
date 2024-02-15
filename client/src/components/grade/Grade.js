import React, {useEffect, useState} from 'react';
import "../../App.css";
import {BASE_URL} from "../../common/constant";
import {Button, Input} from "@mui/material";
import Modal from 'react-modal';

const Grade = () => {

    const [item, setItem] = useState([]);
    const [itemId, setItemId] = useState(-1);
    const [indexCount, setIndexCount] = useState([]);
    const [itemCount, setItemCount] = useState([]);
    const [grade, setGrade] = useState(-1);
    const [switchModal, setSwitchModal] = useState(false);

    const [lesson, setLesson] = useState([]);
    const [lessonId, setLessonId] = useState(null);
    const [student, setStudent] = useState([]);
    const [studentId, setStudentId] = useState(null);
    const [teacher, setTeacher] = useState([]);
    const [teacherId, setTeacherId] = useState(null);
    const [searchKey, setSearchKey] = useState(null);

    const getGradesByDetail = () => {
        fetch(BASE_URL + 'grade/getGradesByDetail')
            .then(response => {
                if (!response.ok) {
                    throw new Error('İşlem şuan gerçekleştirilemiyor');
                }
                return response.json();
            })
            .then(data => {
                setItem(data);
                setIndexCount(data.length + 1);
                setItemCount(data[data.length - 1].id + 1);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    };

    const insertGrade = () => {

        if (grade<0 || grade>100) {
            alert("Not 0 ila 100 arasında olmalı");
            return;
        }

        if (lessonId===null || lessonId==="Select Lesson") {
            alert("Select Lesson!");
            return;
        }

        if (studentId===null || studentId==="Select Student") {
            alert("Select Student!");
            return;
        }

        if (teacherId===null || teacherId==="Select Teacher") {
            alert("Select Teacher!");
            return;
        }

        fetch(BASE_URL + 'grade/save', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                gradeNumber: grade, teacherId: teacherId,
                studentId: studentId, lessonId: lessonId
            })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('İşlem şuan gerçekleştirilemiyor');
                }
                return response.json();
            })
            .then(data => {
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });

        window.location.reload();
    }

    function deleteGrade() {
        fetch(BASE_URL + `grade/deleteGradeById/${itemId}`, {
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

        window.location.reload();
    }

    const findAllLessons = () => {
        fetch(BASE_URL + 'lesson/findAll')
            .then(response => {
                if (!response.ok) {
                    throw new Error('İşlem şuan gerçekleştirilemiyor');
                }
                return response.json();
            })
            .then(data => {
                setLesson(data);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    };

    const selectedLesson = (event) => {
        setLessonId(event.target.value);
    }

    const findAllStudents = () => {
        fetch(BASE_URL + 'student/findAll')
            .then(response => {
                if (!response.ok) {
                    throw new Error('İşlem şuan gerçekleştirilemiyor');
                }
                return response.json();
            })
            .then(data => {
                setStudent(data);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    };

    const selectedStudent = (event) => {
        setStudentId(event.target.value);
    }

    const findAllTeachers = () => {
        fetch(BASE_URL + 'teacher/findAll')
            .then(response => {
                if (!response.ok) {
                    throw new Error('İşlem şuan gerçekleştirilemiyor');
                }
                return response.json();
            })
            .then(data => {
                setTeacher(data);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    };

    const selectedTeacher = (event) => {
        setTeacherId(event.target.value);
    }

    const toggleModal = () => {
        setSwitchModal(!switchModal);
    };

    useEffect(() => {
        getGradesByDetail();
        findAllLessons();
        findAllStudents();
        findAllTeachers();
    }, []);

    return (
        <>
            <Input type="text" placeholder="SearchByLessonName..." className="card"
                   onChange={(e) => setSearchKey(e.target.value)}/>
            <table id="table1">
                <thead>
                <tr>
                    <th></th>
                    <th>ID</th>
                    <th>Grade Score</th>
                    <th>Lesson</th>
                    <th>Student</th>
                    <th>Teacher</th>
                    <th>About</th>
                    <th>Crud</th>
                </tr>
                </thead>
                <tbody>
                {item?.filter(i => !searchKey || i.lessonName.includes(searchKey)).map((item, index) => {
                        return (
                            <tr key={index}>
                                <td>{index + 1}</td>
                                <td>{item.id}</td>
                                <td>{item.gradeScore}</td>
                                <td>{item.lessonName}</td>
                                <td>{item.studentName} {item.studentSurname}</td>
                                <td>{item.teacherName} {item.teacherSurname}</td>
                                <td>
                                    <Button onClick={toggleModal} type="button">Detail</Button>
                                    <Modal
                                        isOpen={switchModal}
                                        onRequestClose={toggleModal}
                                        style={{
                                            overlay: {
                                                backgroundColor: 'rgba(0, 0, 0, 0.5)'
                                            }, content: {
                                                width: '20%',
                                                height: '40%',
                                                margin: 'auto',
                                                backgroundColor: 'white',
                                                border: '1px solid #ccc',
                                                borderRadius: '4px',
                                                outline: 'none',
                                                padding: '20px',
                                                textAlign: "center"
                                            }
                                        }}
                                    >
                                        <h3>Grade Detail</h3>
                                        <table id="table2">
                                            <tbody>
                                            <tr>
                                                <img style={{width: "25%"}} src="/img/g1.png" alt=""/>
                                            </tr>
                                            <tr>
                                                <td>SUBJECT: {item.lessonName}</td>
                                            </tr>
                                            <tr>
                                                <td>PERSONAL SCORE: {item.personalSuccessPercentage} /100</td>
                                            </tr>
                                            <tr>
                                                <td>CLASS SCORE: {item.classSuccessPercentage} /100</td>
                                            </tr>
                                            <tr>
                                                <td>NOTE: {item.note}</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                        <br/>
                                        <button
                                            onClick={toggleModal}>Close Modal
                                        </button>
                                    </Modal>
                                </td>
                                <td>
                                    <Button onMouseUp={() => {
                                        setItemId(item.id)
                                    }}
                                            onClick={deleteGrade}
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
                        <input type="number" placeholder="Enter Grade Score"
                               onChange={e => setGrade(Number(e.target.value))}/>
                    </td>
                    <td>
                        <select value={lessonId} onChange={selectedLesson}>
                            <option>Select Lesson</option>
                            {lesson?.map(les => (
                                <option key={les.id} value={les.id}>{les.name}</option>
                            ))}
                        </select>
                    </td>
                    <td>
                        <select value={studentId} onChange={selectedStudent}>
                            <option>Select Student</option>
                            {student?.map(les => (
                                <option key={les.id} value={les.id}>{les.name} {les.surname}</option>
                            ))}
                        </select>
                    </td>
                    <td>
                        <select value={teacherId} onChange={selectedTeacher}>
                            <option>Select Teacher</option>
                            {teacher?.map(les => (
                                <option key={les.id} value={les.id}>{les.name} {les.surname}</option>
                            ))}
                        </select>
                    </td>
                    <td/>
                    <td>
                        <Button type={"submit"} onClick={insertGrade}>Add</Button>
                    </td>
                </tr>
                </tbody>
            </table>
        </>
    );
};

export default Grade;