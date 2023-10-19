## Structure of components

Each component should implement an `update` function, as specified in
`IComponent`. What each component does in its `update` depends on that
component. For instance, a controller component may fetch all the data
it needs and fill out relevant sections of a common `data` object. A
drive component may fetch the values from the common `data` object and
drive accordingly.