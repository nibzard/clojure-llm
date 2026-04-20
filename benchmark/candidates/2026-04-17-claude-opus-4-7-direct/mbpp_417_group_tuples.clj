(defn group_tuples
  "Write a function to find common first element in given list of lists."
  [Input]
  (when (seq Input)
    (let [first-elements (map first Input)]
      (filter #(= (first first-elements) %) first-elements))))