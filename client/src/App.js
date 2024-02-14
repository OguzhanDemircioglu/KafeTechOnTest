import './App.css';
import Header from "./components/header/Header";
import VerticalMenu from "./components/verticalMenu/VerticalMenu";
import {Route, Routes} from "react-router-dom";
import Teacher from "./components/teacher/Teacher";
import Student from "./components/student/Student";
import Lesson from "./components/lesson/Lesson";
import Home from "./components/home/Home";
import Grade from "./components/grade/Grade";

function App() {
    return (
        <div className="App">
            <Header/>
            <VerticalMenu/>
            <Routes>
                <Route path="/"           element={<Home/>}> </Route>
                <Route path="/teacher"    element={<Teacher />}></Route>
                <Route path="/student"    element={<Student />}></Route>
                <Route path="/lesson"     element={<Lesson />}></Route>
                <Route path="/grade"     element={<Grade />}></Route>
            </Routes>
        </div>
    );
}

export default App;
