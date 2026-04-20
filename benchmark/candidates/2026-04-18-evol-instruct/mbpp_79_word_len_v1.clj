(defn odd-length-word?
  "Return true if `s` is a string whose length is odd, false otherwise.

Examples:
(odd-length-word? \"cat\") => true
(odd-length-word? \"four\") => false
(odd-length-word? nil) => false"
  [s]
  (and (string? s)
       (odd? (count s))))