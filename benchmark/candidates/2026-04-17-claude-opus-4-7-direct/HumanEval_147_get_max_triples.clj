(defn get-max-triples
  "Returns the number of triples (a[i], a[j], a[k]) where i < j < k 
  and a[i] + a[j] + a[k] is a multiple of 3.
  The vector a is defined by a[i] = i * i - i + 1 for 1 <= i <= n."
  [n]
  (let [;; Generate the sequence a[i] = i^2 - i + 1 for i from 1 to n
        ;; We use range 1 to (inc n) because the problem specifies 1 <= i <= n
        a (map #(-> % (* %) (- %) (+ 1)) (range 1 (inc n)))
        
        ;; Calculate the modulo 3 of each element in the sequence
        ;; (mod x 3) returns 0, 1, or 2
        mods (map #(mod % 3) a)
        
        ;; Count the occurrences of each remainder (0, 1, 2)
        freq (frequencies mods)
        
        ;; Extract counts, defaulting to 0 if a remainder doesn't exist
        c0 (get freq 0 0)
        c1 (get freq 1 0)
        c2 (get freq 2 0)]
    
    ;; A sum is divisible by 3 in the following cases:
    ;; 1. All three numbers are divisible by 3 (0 + 0 + 0 = 0)
    ;; 2. All three numbers have remainder 1 (1 + 1 + 1 = 3)
    ;; 3. All three numbers have remainder 2 (2 + 2 + 2 = 6)
    ;; 4. One number has each remainder (0 + 1 + 2 = 3)
    
    ;; Calculate combinations for each case using nCk = n! / (k! * (n-k)!)
    ;; For k=3, nC3 = n * (n-1) * (n-2) / 6
    (+ 
      ;; Case 1: Choose 3 from c0
      (/ (* c0 (dec c0) (- c0 2)) 6)
      
      ;; Case 2: Choose 3 from c1
      (/ (* c1 (dec c1) (- c1 2)) 6)
      
      ;; Case 3: Choose 3 from c2
      (/ (* c2 (dec c2) (- c2 2)) 6)
      
      ;; Case 4: Choose 1 from c0, 1 from c1, and 1 from c2
      (* c0 c1 c2))))