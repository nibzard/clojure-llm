(defn next_smallest_palindrome
  "Write a function to find the next smallest palindrome of a specified integer, returned as an integer."
  [num]
  (let [n (if (nil? num) 0 num)
        s (str n)
        len (count s)
        half-len (quot len 2)
        left (subs s 0 half-len)
        middle (if (odd? len) (str (nth s half-len)) "")
        right (subs s (+ half-len (if (odd? len) 1 0)))
        
        ;; Helper to construct palindrome from left part and middle
        make-pal (fn [l m] (bigint (str l m (apply str (reverse l)))))
        
        ;; Check if the current number is already a palindrome
        current-pal (make-pal left middle)
        
        ;; If the generated palindrome is smaller than or equal to the original number,
        ;; we need to increment the "prefix" (left + middle) and generate a new palindrome.
        ;; The prefix is treated as a number to handle carry-over (e.g., 99 -> 100).
        prefix (bigint (str left middle))
        next-prefix (inc prefix)
        next-prefix-str (str next-prefix)
        
        ;; Calculate new left and middle parts from the incremented prefix
        ;; If the length of the prefix increases (e.g. 99 -> 100), the palindrome length increases.
        next-len (count next-prefix-str)
        next-left (subs next-prefix-str 0 (quot next-len 2))
        next-middle (if (odd? next-len) (str (nth next-prefix-str (quot next-len 2))) "")
        
        next-pal (make-pal next-left next-middle)]
    
    (if (>= current-pal n)
      current-pal
      next-pal)))