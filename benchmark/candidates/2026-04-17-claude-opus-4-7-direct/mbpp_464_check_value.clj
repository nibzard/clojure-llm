(defn check_value
  "Write a function to check if all values are same in a map."
  [dict n]
  (let [vals (vals dict)]
    (every? #(= % n) vals)))