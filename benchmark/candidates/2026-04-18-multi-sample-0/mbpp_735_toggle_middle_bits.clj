(defn toggle_middle_bits
  "	Write a cljthon function to toggle bits of the number except the first and the last bit. https://www.geeksforgeeks.org/toggle-bits-number-expect-first-last-bits/"
  [n]
  (if (or (nil? n) (< n 5))
    n
    (let [bits (Long/toBinaryString (long n))
          len  (count bits)]
      (Long/parseLong
       (apply str
              (first bits)
              (map #(if (= % \0) \1 \0) (subs bits 1 (dec len)))
              (nth bits (dec len)))
       2))))