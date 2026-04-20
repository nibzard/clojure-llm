(defn dog_age
  "Return a map of age conversions for a dog's age.

  Accepts either an integer age in years or nil.
  For a non-negative age n, return:
  {:dog-years n
   :human-years (* 7 n)
   :puppy? true if n < 2}

  Examples:
  (dog_age 3) => {:dog-years 3, :human-years 21, :puppy? false}
  (dog_age 1) => {:dog-years 1, :human-years 7, :puppy? true}
  (dog_age nil) => nil"
  [h_age])