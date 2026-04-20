(defn first_odd
  "Write a cljthon function to find the first odd number in a given list of numbers."
  [nums]
  (some #(when (odd? %) %) nums))