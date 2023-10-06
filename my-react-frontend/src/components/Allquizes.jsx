import { useEffect, useState } from 'react';
import { Paper, Typography, Button,Grid,Card,CardContent } from '@mui/material';
import PropTypes from 'prop-types';

const PAGE_SIZE = 9; // Number of quizzes per page

const QuizList = ({ onSelectQuiz }) => {
    const userEmail = JSON.parse(localStorage.getItem('user')).email;
    const CreatorName = JSON.parse(localStorage.getItem('user')).employeename;

    const [allQuizzes, setAllQuizzes] = useState([]);  // Stores all quizzes
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(1);
    // Assuming a default of one page
    useEffect(() => {
        fetch(`http://localhost:8090/api/v1/employee/${userEmail}/quizzes`)  // Use the new endpoint
            .then(res => res.json())
            .then(data => {
                setAllQuizzes(data);
                setTotalPages(Math.ceil(data.length / PAGE_SIZE));
            })
            .catch(err => console.error("Failed to fetch quizzes:", err));
    }, [userEmail]);
   /* useEffect(() => {
        fetch(`http://localhost:8090/api/quizzes`)
            .then(res => res.json())
            .then(data => {
                setAllQuizzes(data);
                setTotalPages(Math.ceil(data.length / PAGE_SIZE));
            })
            .catch(err => console.error("Failed to fetch quizzes:", err));
    }, []);*/

    // Get quizzes for the current page
    const currentQuizzes = allQuizzes.slice((currentPage - 1) * PAGE_SIZE, currentPage * PAGE_SIZE);

    return (
        <div>
            <Grid container spacing={3}>
                {currentQuizzes.map((quiz, index) => (
                    <Grid item xs={12} sm={6} md={4} key={index}>
                        <Card elevation={3}>
                            <CardContent
                                style={{
                                    minHeight: '20px',
                                    display: 'flex',
                                    flexDirection: 'column',
                                    justifyContent: 'space-between',
                                    backgroundColor: 'rgba(51, 51, 51, 1)',
                                }}
                                onClick={() => {
                                    // Store the selected quiz ID in localStorage
                                    localStorage.setItem('chosenQuizId', quiz.id);

                                    // Pass the entire quiz object to the onSelectQuiz callback
                                    onSelectQuiz(quiz);
                                }}
                            >
                                <Typography variant="h6" style={{ textAlign: 'center', color: '#7FFFD4' }}>
                                    Title: {quiz.title}
                                </Typography>
                                <Typography variant="h7" style={{ textAlign: 'center', color: '#7FFFD4' }}>
                                    By :{CreatorName}
                                </Typography>
                            </CardContent>
                        </Card>
                    </Grid>
                ))}

                ))}
            </Grid>

            <div style={{ display: 'flex', justifyContent: 'center', margin: '20px' }}>
                <Button
                    disabled={currentPage === 1}
                    onClick={() => setCurrentPage((prev) => prev - 1)}>
                    Previous
                </Button>
                <Typography style={{ margin: '0 10px' }}>{currentPage} / {totalPages}</Typography>
                <Button
                    disabled={currentPage === totalPages}
                    onClick={() => setCurrentPage((prev) => prev + 1)}>
                    Next
                </Button>
            </div>
        </div>
    );
};

QuizList.propTypes = {
    onSelectQuiz: PropTypes.func.isRequired,
    userEmail: PropTypes.string.isRequired, // Add userEmail propType

};

export default QuizList;
