(defn find_even_pair
  "Write a function that counts the number of pairs of integers in a list that xor to an even number."
  [A]
  (let [cnt-odd (count (filter odd? A))
        cnt-even (count (filter even? A))]
    (+ (/ (* cnt-odd (dec cnt-odd)) 2)
       (/ (* cnt-even (dec cnt-even)) 2))))