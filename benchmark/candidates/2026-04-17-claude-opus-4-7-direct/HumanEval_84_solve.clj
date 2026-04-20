(defn solve
  "Given a positive integer N, return the total sum of its digits in binary."
  [N]
  (->> (str N)
       (map #(Character/digit ^char % 10))
       (reduce +)
       (Integer/toBinaryString)))