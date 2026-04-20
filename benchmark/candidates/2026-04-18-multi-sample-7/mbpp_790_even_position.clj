(defn even_position
  "	Write a cljthon function to check whether every even index contains even numbers of a given list."
  [nums]
  (every?
   (fn [[idx n]])
     (or (odd? idx) (even? n)))
   (map-indexed vector nums)))