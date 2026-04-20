(defn first_Digit
  "Write a cljthon function to find the first digit of a given number."
  [n]
  (->> (str n)
       (filter #(Character/isDigit %))
       first
       (#(Character/digit % 10))))