(defn dog_age
  "Write a function to calculate a dog's age in dog's years."
  [h_age]
  (when (some? h_age)
    (* h_age 7)))