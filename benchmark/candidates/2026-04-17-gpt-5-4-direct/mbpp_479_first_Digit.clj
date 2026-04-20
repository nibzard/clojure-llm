(defn first_Digit
  "	Write a cljthon function to find the first digit of a given number."
  [n]
  (let [s (str (if (number? n) (Math/abs (long n)) n))]
    (when-let [ch (first (re-seq #"\d" s))]
      (Character/digit ^char (first ch) 10))))