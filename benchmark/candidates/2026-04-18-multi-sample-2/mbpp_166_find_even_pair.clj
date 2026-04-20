(defn find_even_pair
  "	Write a function that counts the number of pairs of integers in a list that xor to an even number."
  [A]
  (let [freqs (frequencies (map #(mod % 2) A))
        evens (get freqs 0 0)
        odds  (get freqs 1 0)
        choose2 (fn [n] (quot (* n (dec n)) 2))]
    (+ (choose2 evens)
       (choose2 odds))))