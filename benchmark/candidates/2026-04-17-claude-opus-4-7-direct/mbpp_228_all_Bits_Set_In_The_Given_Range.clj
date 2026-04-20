(defn all-Bits-Set-In-The-Given-Range
  "Check whether all the bits are set in the given range [l, r] for number n.
   Returns true if all bits from position l to r (inclusive) are 1, false otherwise."
  [n l r]
  (let [mask (bit-shift-left (bit-not (bit-shift-left -1 (- r l -1))) l)]
    (= (bit-and n mask) mask)))