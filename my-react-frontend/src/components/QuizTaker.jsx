import { useState } from 'react';
import { Typography, Radio, RadioGroup, FormControlLabel, Button, Paper } from '@mui/material';
import PropTypes from 'prop-types';

const QuizTaker = ({ quiz, onFinish }) => {
    const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
    const [selectedOption, setSelectedOption] = useState('');
    const [answers, setAnswers] = useState([]);

    if (!quiz || !quiz.questions || quiz.questions.length === 0) {
        return <Typography variant="h6">No questions available.</Typography>;
    }

    const handleNext = () => {
        // Collect answer
        setAnswers(prevAnswers => [
            ...prevAnswers,
            {
                question: {
                    id: quiz.questions[currentQuestionIndex].id
                },
                userResponse: parseInt(selectedOption)
            }
        ]);

        if (currentQuestionIndex < quiz.questions.length - 1) {
            setCurrentQuestionIndex(prevIndex => prevIndex + 1);
            setSelectedOption('');
        } else {
            const finalScore = answers.reduce((acc, answer) => {
                if (quiz.questions.find(q => q.id === answer.question.id).correctOption === answer.userResponse) {
                    return acc + 1;
                }
                return acc;
            }, 0) / quiz.questions.length * 100;

            const user = JSON.parse(localStorage.getItem('user'));

            fetch(`http://localhost:8090/api/quiz/submit/${user.employeeid}/${localStorage.getItem('chosenQuizId')}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    userName: user.employeename,
                    quizTitle: quiz.title,
                    score: finalScore,
                    email: user.email
                })
            })
                .then(response => response.json())
                .then(data => {
                    console.log('Success:', data);
                    onFinish(finalScore);
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        }
    };

    const currentQuestion = quiz.questions[currentQuestionIndex];

    if (!currentQuestion) {
        return <Typography variant="h6">Invalid question format.</Typography>;
    }

    return (
        <Paper elevation={3} style={{
            padding: '30px',
            maxWidth: '500px',
            margin: '25px auto',
            backgroundColor: 'rgba(51, 51, 51, 0.7)',
            borderRadius: '15px',
            textAlign: 'center',
        }}>
            <Typography variant="h5">{currentQuestion.questionTitle}</Typography>
            <RadioGroup style={{ color: 'white' }} value={selectedOption} onChange={e => setSelectedOption(e.target.value)}>
                <FormControlLabel value="1" control={<Radio />} label={currentQuestion.option1} />
                <FormControlLabel value="2" control={<Radio />} label={currentQuestion.option2} />
                <FormControlLabel value="3" control={<Radio />} label={currentQuestion.option3} />
                <FormControlLabel value="4" control={<Radio />} label={currentQuestion.option4} />
            </RadioGroup>
            <Button variant="contained" color="primary" onClick={handleNext}>
                {currentQuestionIndex < quiz.questions.length - 1 ? "Next" : "Finish"}
            </Button>
        </Paper>
    );
};

QuizTaker.propTypes = {
    quiz: PropTypes.shape({
        questions: PropTypes.arrayOf(PropTypes.shape({
            id: PropTypes.number.isRequired,
            questionTitle: PropTypes.string.isRequired,
            option1: PropTypes.string.isRequired,
            option2: PropTypes.string.isRequired,
            option3: PropTypes.string.isRequired,
            option4: PropTypes.string.isRequired,
            correctOption: PropTypes.number.isRequired
        })).isRequired
    }).isRequired,
    onFinish: PropTypes.func.isRequired
};

export default QuizTaker;
