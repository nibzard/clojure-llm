(defn find_Odd_Pair
  "Write a clojure function to count the number of pairs whose xor value is odd."
  [A N]
  (let [evens (count (filter even? A))
        odds (count (filter odd? A))]
    (* evens odds)))