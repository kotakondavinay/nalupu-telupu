import express, { Request, Response } from 'express';
import  tasks from './tasks/tasks';

const app = express();
const port = process.env.PORT || 3000;

app.use(express.json());
app.use('/tasks', tasks);

app.get('/', (req: Request, res: Response) => {
    res.send('Hello, TypeScript Express!');
  });

  app.listen(port, () => {
    console.log(`Server running at http://localhost:${port}`);
  });
