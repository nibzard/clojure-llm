(defn first_Digit
  "	Write a cljthon function to find the first digit of a given number."
  [n]
  (let [s (-> n str (clojure.string/replace #"^-?" ""))]
    (when (seq s)
      (Character/digit ^char (first s) 10))))