(defn prime_length
  "Write a function that takes a string and returns true if the string
  length is a prime number or false otherwise
  Examples
  >>> (prime_length \"Hello\")
  true
  >>> (prime_length \"abcdcba\")
  true
  >>> (prime_length \"kittens\")
  true
  >>> (prime_length \"orange\")
  false"
  [string]
  (let [len (count string)]
    (cond
      (< len 2) false
      (== len 2) true
      (even? len) false
      :else (not (some (fn [d] (zero? (mod len d)))
                       (range 3 (inc (Math/sqrt len)) 2))))))