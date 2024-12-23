## 1. Setup
Maven:
```xml
<repository>
  <id>jitpack.io</id>
  <url>https://jitpack.io</url>
</repository>
```
```xml
<dependency>
  <groupId>com.github.KorshunK</groupId>
  <artifactId>JBrawlStarsAPI</artifactId>
  <version>Version</version>
</dependency>
```

Gradle:

```kt
dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
          mavenCentral()
          maven { url 'https://jitpack.io'
    }
  }
}
```

```kt

dependencies {
  implementation 'com.github.KorshunK:JBrawlStarsAPI:Version'
}

```


### To get started, you need to get an [API KEY](https://developer.brawlstars.com/#/account)
After that, you need to copy the secret token, __DO NOT SHOW IT TO ANYONE!__
In order for the library to work, you need to set the API KEY value

![API KEY set show](resources/api_key.png)

The library is now ready for use.

## 2. Objects
### Brawlers
Brawler - Object of the brawler

Gadget - Object of the brawler`s gadget

StarPower - Object of the brawler`s star power

PlayerBrawler - Object of the player`s brawler

### Players
Player - Object of the player

Club - Object of the club

ClubMember - Object of the player, who is a member of the club

Battle - Object of the player`s battle

## 3. Getting
### Brawlers
Object of the brawler can be obtained in two ways: get it by ID or name

Getting by ID:

![getting brawler by id](resources/brawler_by_id.png)

Getting by name:

![getting brawler by name](resources/brawler_by_name.png)
### Players
A player can be obtained by a tag that starts with '#'

![getting player](resources/getting_player.png)

### Clubs
A club can be obtained by a tag that starts with '#'

![getting club](resources/getting_club.png)

## 4. Examples
Getting player name:


![Example 1](resources/example_1.png)

Getting player battle log:

![Example 2](resources/example_2.png)

Getting player brawlers:

![Example 3](resources/example_3.png)

Getting player club members:

![Example 4](resources/example_4.png)
