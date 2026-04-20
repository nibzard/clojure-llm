(defn find_even_pair
  "Write a function that counts the number of pairs of integers in a list that xor to an even number."
  [A]
  (let [n (count A)
        e (count (filter even? A))
        o (- n e)]
    (+ (quot (* e (dec e)) 2)
       (quot (* o (dec o)) 2))))