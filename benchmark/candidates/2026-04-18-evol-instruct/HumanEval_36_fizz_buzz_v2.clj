(defn fizz_buzz
  "Return a lazy sequence of all integers less than n that are divisible by 11 or 13 and contain the digit 7.
  The result must be in ascending order.

  >>> (fizz_buzz 50)
  ()
  >>> (fizz_buzz 78)
  (77)
  >>> (fizz_buzz 150)
  (77 143)"
  [n]
  (filter
    #(and (or (zero? (mod % 11))
            (zero? (mod % 13)))
          (re-find #"7" (str %)))
    (range n)))