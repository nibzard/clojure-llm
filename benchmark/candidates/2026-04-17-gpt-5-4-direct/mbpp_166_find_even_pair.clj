(defn find_even_pair
  "	Write a function that counts the number of pairs of integers in a list that xor to an even number."
  [A]
  (let [freqs (frequencies (map #(bit-and % 1) (or A [])))
        evens (get freqs 0 0)
        odds  (get freqs 1 0)]
    (+ (/ (* evens (dec evens)) 2)
       (/ (* odds (dec odds)) 2))))