(defn sample_nam
  "	Write a function to sum the length of the names of a given list of names after removing the names that start with a lowercase letter."
  [sample_names]
  (->> sample_names
       (remove #(when-let [s (seq %)]
                  (Character/isLowerCase ^Character (first s))))
       (map count)
       (reduce + 0)))