import { Router, Request, Response } from "express";
import { Task } from "./task";

const router = Router()
let tasks: Task[] = []

router.post('/', (req: Request, res: Response) => {
    const task: Task = {
        id: tasks.length,
        title: req.body.title || "",
        description: req.body.description || "",
        completed: false
    }
    tasks.push(task);

    res.status(201).json(tasks)
});

router.get('/', (req: Request, res: Response) => {
    res.json(tasks)
});

router.get('/:id', (req: Request, res: Response) => {
    const id = req.params.id;
    const task = tasks.find(task => task.id == parseInt(id));
    if (!task) {
        res.status(404).json({ message: 'Task not found' });
        return;
    }

    res.json(task);
});

router.put('/:id', (req: Request, res: Response) => {
    const id = req.params.id;
    const task = tasks.find(task => task.id == parseInt(id));
    if (!task) {
        res.status(404).json({ message: 'Task not found' });
        return;
    }

    task.title = req.body.title || task.title;
    task.description = req.body.description || task.description;
    task.completed = req.body.completed || task.completed;

    res.json(task);

});


export default router;