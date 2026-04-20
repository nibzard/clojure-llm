(defn digits
  [n]
  (let [odds (filter odd? (map #(Character/digit ^char % 10) (str n)))]
    (if (seq odds)
      (reduce * odds)
      0)))