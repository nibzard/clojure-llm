(defn empty_list
  "Write a function to create a list of N empty dictionaries."
  [length]
  (let [n (max 0 (or length 0))]
    (apply list (repeat n {}))))