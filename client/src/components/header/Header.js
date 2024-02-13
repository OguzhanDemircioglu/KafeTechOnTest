import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faPencilAlt} from "@fortawesome/free-solid-svg-icons";
import {NavLink} from "react-router-dom";
import {Nav, Navbar} from "react-bootstrap";
import "./Header.css";

export default function Header() {
    return (
        <Navbar bg="secondary" variant="dark" expand="md">
            <Navbar.Brand href="/124" style={{color: "gold", marginLeft: "20px"}}>
                <FontAwesomeIcon icon={faPencilAlt}/>
                School Tracer
            </Navbar.Brand>
            <Nav
                className="me-auto my-2 my-lg-0"
            >
            </Nav>
            <NavLink className="nav-link" style={{color: "gold"}} href="/" to="">
                Login
            </NavLink>
            <NavLink className="nav-link" style={{color: "gold", marginLeft: "20px", marginRight: "20px"}} href="/"
                     to="">
                Register
            </NavLink>
        </Navbar>
    );
};