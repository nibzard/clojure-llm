(defn dog-age
  "Given a dog's age in human years, return a map with the age in dog years and cat years.

  Assumptions:
  - 1 human year = 7 dog years
  - 1 human year = 6 cat years
  - If h-age is nil, return nil.

  Examples:
  (dog-age 3) => {:dog-years 21 :cat-years 18}
  (dog-age 0) => {:dog-years 0 :cat-years 0}
  (dog-age nil) => nil"
  [h-age]
  (when (some? h-age)
    {:dog-years (* 7 h-age)
     :cat-years (* 6 h-age)}))