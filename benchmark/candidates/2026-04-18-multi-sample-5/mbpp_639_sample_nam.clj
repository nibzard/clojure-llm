(defn sample_nam
  "	Write a function to sum the length of the names of a given list of names after removing the names that start with a lowercase letter."
  [sample_names]
  (->> sample_names
       (filter #(and (string? %)
                     (not (empty? %))
                     (let [c (first %)]
                       (Character/isUpperCase ^char c))))
       (map count)
       (reduce + 0)))