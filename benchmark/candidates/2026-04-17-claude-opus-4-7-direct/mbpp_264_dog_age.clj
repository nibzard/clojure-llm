(defn dog_age
  "Write a function to calculate a dog's age in dog's years."
  [h_age]
  (cond
    (nil? h_age) nil
    (<= h_age 2) (* h_age 10.5)
    :else (+ 21 (* (- h_age 2) 4))))