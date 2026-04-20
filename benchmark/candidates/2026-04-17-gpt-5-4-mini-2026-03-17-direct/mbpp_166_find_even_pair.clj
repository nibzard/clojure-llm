(defn find_even_pair
  "Write a function that counts the number of pairs of integers in a list that xor to an even number."
  [A]
  (let [even-count (count (filter even? A))
        odd-count  (count (filter odd? A))]
    (+ (quot (* even-count (dec even-count)) 2)
       (quot (* odd-count (dec odd-count)) 2))))