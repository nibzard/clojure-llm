(defn fruit_distribution
  "Given a string representing the number of apples and oranges, and the total 
  number of fruits, return the number of mangoes."
  [s n]
  (let [numbers (re-seq #"\d+" s)
        apples (if (first numbers) (parse-long (first numbers)) 0)
        oranges (if (second numbers) (parse-long (second numbers)) 0)]
    (- n apples oranges)))