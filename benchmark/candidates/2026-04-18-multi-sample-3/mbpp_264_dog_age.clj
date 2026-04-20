(defn dog_age
  "	Write a function to calculate a dog's age in dog's years."
  [h_age]
  (when (number? h_age)
    (* h_age 7)))