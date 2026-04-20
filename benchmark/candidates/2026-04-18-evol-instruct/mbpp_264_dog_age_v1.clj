(defn dog-age
  "Return a map with a dog's age converted into dog years and human years per stage.

  Rules:
  - Accept an age in years, which may be a number or nil.
  - Return {:human age :dog dog-years :stage stage}.
  - Dog years are 7x human years.
  - Stage is one of:
    :puppy for ages < 2
    :adult for ages < 8
    :senior for ages >= 8
  - If age is nil, treat it as 0.

  Examples:
  (dog-age 3) ;=> {:human 3, :dog 21, :stage :adult}
  (dog-age nil) ;=> {:human 0, :dog 0, :stage :puppy}"
  [h-age]
  (let [age (or h-age 0)]
    {:human age
     :dog (* 7 age)
     :stage (cond
              (< age 2) :puppy
              (< age 8) :adult
              :else :senior)}))