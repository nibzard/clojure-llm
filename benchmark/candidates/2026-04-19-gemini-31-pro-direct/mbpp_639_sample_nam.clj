(defn sample_nam
  "Write a function to sum the length of the names of a given list of names after removing the names that start with a lowercase letter."
  [sample_names]
  (->> sample_names
       (remove #(let [f (first %)]
                  (and f (Character/isLowerCase ^Character f))))
       (map count)
       (reduce + 0)))